package Models;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

public class Download extends Observable implements Runnable {

    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;
    public static final int CANCELLED = 3;
    public static final int ERROR = 4;
    private static int BUFFER_SIZE = 1024;
    public static String[] STATUSES = {"Downloading", "Paused", "Complete", "Cancelled", "Error"};
    private URL url;
    private long size;
    private long downloaded; // number of bytes downloaded
    private int status; // current status of download
    private long initTime; //initial time when download started or resumed
    private long startTime; // start time for current bytes
    private long readSinceStart; // number of bytes downloaded since startTime
    private long elapsedTime = 0; // elapsed time till now
    private long prevElapsedTime = 0; // time elapsed before resuming download
    private long remainingTime = -1; //time remaining to finish download
    private float avgSpeed = 0;
    private float speed = 0; //KB/s

    public Download(URL url) {
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DOWNLOADING;
        // Begin the download.
        download();
    }

    private void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public String getUrl() {
        return url.toString();
    }

    public String getSize() {
        return size + "";
    }

    public String getSpeed() {
        return speed + "";
    }

    public String getElapsedTime() {
        return formatTime(elapsedTime / 1000000000);
    }

    // Get remaining time
    public String getRemainingTime() {
        if (remainingTime < 0) return "Unknown";
        else return formatTime(remainingTime);
    }

    // Format time
    public String formatTime(long time) { //time in seconds
        String s = "";
        s += (String.format("%02d", time / 3600)) + ":";
        time %= 3600;
        s += (String.format("%02d", time / 60)) + ":";
        time %= 60;
        s += String.format("%02d", time);
        return s;
    }

    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }

    // Get this download's status.
    public int getStatus() {
        return status;
    }

    public void pause() {
        prevElapsedTime = elapsedTime;
        status = PAUSED;
        stateChanged();
    }

    // Resume this download.
    public void resume() {
        status = DOWNLOADING;
        stateChanged();
        download();
    }

    // Cancel this download.
    public void cancel() {
        prevElapsedTime = elapsedTime;
        status = CANCELLED;
        stateChanged();
    }

    // Mark this download as having an error.
    private void error() {
        prevElapsedTime = elapsedTime;
        status = ERROR;
        stateChanged();
    }

    private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }


    public void run() {
        RandomAccessFile file = null;
        InputStream stream = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
            connection.connect();
            if (connection.getResponseCode() / 100 != 2) {
                error();
            }

            // Check for valid content length.
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                error();
            }

            // set size
            if (size == -1) {
                size = contentLength;
                stateChanged();
            }
            int i = 0;
            String[] aray = url.getPath().split("[./]");
            String PATH = "C:\\Users\\thina-pc\\Downloads\\";
            String FORMAT = aray[aray.length - 1];
            if (FORMAT.equals("mkv") || FORMAT.equals("mp4"))
                PATH = PATH + "Video";
            else if (FORMAT.equals("pdf"))
                PATH = PATH + "Documents";
            else if (FORMAT.equals("exe"))
                PATH = PATH + "Programs";
            file = new RandomAccessFile(PATH + getFileName(url), "rw");
            file.seek(downloaded);

            stream = connection.getInputStream();
            initTime = System.nanoTime();
            while (status == DOWNLOADING) {


                if (i == 0) {
                    startTime = System.nanoTime();
                    readSinceStart = 0;
                }
                byte[] buffer;
                if (size - downloaded > BUFFER_SIZE) {
                    buffer = new byte[BUFFER_SIZE];
                } else {
                    buffer = new byte[(int) (size - downloaded)];
                }
                // Read from server into buffer.
                int read = stream.read(buffer);
                if (read == -1)
                    break;
                // Write buffer to file.
                file.write(buffer, 0, read);
                downloaded += read;
                readSinceStart += read;
                //update speed
                i++;
                if (i >= 50) {
                    speed = (readSinceStart * 976562.5f) / (System.nanoTime() - startTime);
                    if (speed > 0) remainingTime = (long) ((size - downloaded) / (speed * 1024));
                    else remainingTime = -1;
                    elapsedTime = prevElapsedTime + (System.nanoTime() - initTime);
                    avgSpeed = (downloaded * 976562.5f) / elapsedTime;
                    i = 0;
                }
                stateChanged();
            }
            if (status == DOWNLOADING) {
                status = COMPLETE;
                stateChanged();
            }
        } catch (Exception e) {
            System.out.println(e);
            error();
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close();
                } catch (Exception ignored) {
                }
            }

            // Close connection
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    private void stateChanged() {
        setChanged();
        notifyObservers();
    }

}
