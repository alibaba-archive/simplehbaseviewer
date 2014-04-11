package allen.test.perf;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author xinzhi.zhang
 * */
public class MD5Perf {

    protected static Log log = LogFactory.getLog(MD5Perf.class);

    @Test
    public void testMD5Perf() throws Exception {

        int loop = 1000000;

        long start = System.currentTimeMillis();
        while (loop-- > 0) {
            String userId = "1234567887654321";
            DigestUtils.md5Hex(userId.getBytes("utf-8"));
        }

        long end = System.currentTimeMillis();
        long consumeTime = end - start;

        log.info(consumeTime);
    }
}
