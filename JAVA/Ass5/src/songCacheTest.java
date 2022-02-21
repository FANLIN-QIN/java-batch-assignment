import org.junit.Assert;
import org.junit.Test;


public class songCacheTest{

    @Test
     public void SongCacheTest(){
        SongCache sc = new SongCacheImpl();
        //测试null
        sc.recordSongPlays(null, 2);
        //测试 同样的ID 多次相加
        sc.recordSongPlays("ID-1", 3);
        sc.recordSongPlays("ID-1", 5);

        sc.recordSongPlays("ID-4", 9);
        sc.recordSongPlays("ID-2", 1);
        sc.recordSongPlays("ID-3", 5);
        sc.recordSongPlays("ID-5", 2);


        //测试空值和正常值
        Assert.assertEquals(8, sc.getPlayForSong("ID-1"));
        Assert.assertEquals(-1,sc.getPlayForSong("ID-8"));

        //先测试size是否正确，再测试结果(正常情况)
        Assert.assertEquals(3,sc.getTopNSongsPlayed(3).size());
        String[] test1 = {"ID-4","ID-1","ID-3"};
        Assert.assertArrayEquals(test1, sc.getTopNSongsPlayed(3).toArray());

        //先测试size是否正确，再测试结果（null)
        Assert.assertEquals(0,sc.getTopNSongsPlayed(0).size());
        String[] test2 = {};
        Assert.assertArrayEquals(test2, sc.getTopNSongsPlayed(0).toArray());

        //如果n > sc 本身的长度,应该降序返回所有的值
        Assert.assertEquals(5,sc.getTopNSongsPlayed(7).size());
        String[] test3 = {"ID-4","ID-1","ID-3","ID-5","ID-2"};
        Assert.assertArrayEquals(test3, sc.getTopNSongsPlayed(7).toArray());
    }
}
