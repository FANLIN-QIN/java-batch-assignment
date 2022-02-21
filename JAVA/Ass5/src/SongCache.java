import java.util.List;


public interface SongCache {

    void recordSongPlays(String songID, int numPlays);

    int getPlayForSong(String songID);

    List<String> getTopNSongsPlayed(int n);

}
