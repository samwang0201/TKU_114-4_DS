abstract class MediaFile {
    protected String fileName;

    public MediaFile(String fileName) {
        this.fileName = fileName;
    }

    public abstract void process();
}

interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

class ImageFile extends MediaFile implements Compressible {

    public ImageFile(String fileName) {
        super(fileName);
    }

    @Override
    public void process() {
        System.out.println(fileName + "：處理圖片檔案");
    }

    @Override
    public void compress() {
        System.out.println(fileName + "：壓縮圖片");
    }
}

class AudioFile extends MediaFile implements Playable, Compressible {

    public AudioFile(String fileName) {
        super(fileName);
    }

    @Override
    public void process() {
        System.out.println(fileName + "：處理音訊檔案");
    }

    @Override
    public void play() {
        System.out.println(fileName + "：播放音訊");
    }

    @Override
    public void compress() {
        System.out.println(fileName + "：壓縮音訊");
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {

    public VideoFile(String fileName) {
        super(fileName);
    }

    @Override
    public void process() {
        System.out.println(fileName + "：處理影片檔案");
    }

    @Override
    public void play() {
        System.out.println(fileName + "：播放影片");
    }

    @Override
    public void compress() {
        System.out.println(fileName + "：壓縮影片");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {

        MediaFile[] files = {
            new ImageFile("photo.jpg"),
            new AudioFile("music.mp3"),
            new VideoFile("movie.mp4")
        };

        for (int i = 0; i < files.length; i++) {

            files[i].process();

            if (files[i] instanceof Playable playable) {
                playable.play();
            }

            if (files[i] instanceof Compressible compressible) {
                compressible.compress();
            }

            System.out.println("--------------------");
        }
    }
}