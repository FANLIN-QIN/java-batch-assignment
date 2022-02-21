import java.util.*;

public class SongCacheImpl implements SongCache{
    Map<String,Integer> record = new HashMap<>();

    @Override
    public void recordSongPlays(String songID, int numPlays) {
        if(songID == null) return;
        if(record.containsKey(songID)){
            int num = record.get(songID);
            record.put(songID, num + numPlays);
        }else{
            record.put(songID,numPlays);
        }

    }

    @Override
    public int getPlayForSong(String songID) {
        return record.getOrDefault(songID, -1);

    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        if(n == 0) return new ArrayList<>();
        PriorityQueue<String> pq = new PriorityQueue<>((n1,n2) -> record.get(n1) - record.get(n2));
        for(String temp:record.keySet()){
            pq.add(temp);
            if(pq.size() > n){
                pq.poll();
            }
        }
        //min-heap ---反过来输出结果
        String[] ans = new String[pq.size()];
        for(int i = pq.size() - 1; i >= 0; i--){
            ans[i] = pq.poll();
        }
        //array to list
        List<String> res = new ArrayList<>();
        Collections.addAll(res, ans);
        return res;
    }
}
