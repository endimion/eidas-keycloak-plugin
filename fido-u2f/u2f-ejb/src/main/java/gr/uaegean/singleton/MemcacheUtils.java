/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uaegean.singleton;

import java.io.IOException;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;

/**
 *
 * @author nikos
 */
public class MemcacheUtils {

    public static MemcachedClient mcc;
    public static boolean isInit = false;

    private static void initMemcache() throws IOException {
        MemcachedClient mcc = new MemcachedClient(new ConnectionFactoryBuilder().setDaemon(true).setFailureMode(FailureMode.Retry).build(),
                AddrUtil.getAddresses("memcached:11211"));
        MemcacheUtils.mcc = mcc;
        MemcacheUtils.isInit = true;
    }

    public static MemcachedClient getCache() throws IOException {
        if (!MemcacheUtils.isInit) {
            initMemcache();
            MemcacheUtils.isInit = true;
        }
        return MemcacheUtils.mcc;
    }

}
