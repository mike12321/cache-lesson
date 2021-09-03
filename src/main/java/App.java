import com.algoritms.MergeSort;
import com.cache.CacheService;
import com.cache.CacheServiceGuavaImpl;
import com.cache.CacheServiceImpl;

import java.util.Arrays;
public class App {
    public static void main(String[] args ) {
        CacheService cacheService = new CacheServiceImpl();

        cacheService.put("key1", "test1", 10000);
        cacheService.put("key2", "test2", 10000);
        cacheService.put("key3", "test3", 10000);
        cacheService.put("key4", "test4", 10000);

        System.out.println(cacheService.size());
        System.out.println(cacheService.get("key1"));
        System.out.println(cacheService.getAverageTimePuttingIntoCache());
        cacheService.printStats();


        CacheService guavaCacheService = new CacheServiceGuavaImpl();

        guavaCacheService.put("test22");
        guavaCacheService.put("test23");
        guavaCacheService.put("hello");
        guavaCacheService.put("test25");
        guavaCacheService.put("test26");
        System.out.println(guavaCacheService.size());
        System.out.println(guavaCacheService.get("hello"));
        System.out.println(guavaCacheService.getAverageTimePuttingIntoCache());
        guavaCacheService.printStats();
    }
}
