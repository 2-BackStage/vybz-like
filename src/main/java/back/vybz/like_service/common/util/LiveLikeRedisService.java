package back.vybz.like_service.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveLikeRedisService  {

    private  final StringRedisTemplate stringRedisTemplate;
    private static final String REDIS_LIKE_PREFIX = "live:like:";
    public Long increment(String streamKey) {
        return stringRedisTemplate.opsForValue().increment(getKey(streamKey));
    }

    public Long incrementBy(String streamKey, long value) {
        return stringRedisTemplate.opsForValue().increment(getKey(streamKey), value);
    }

    public Long getCount(String streamKey) {
        String result = stringRedisTemplate.opsForValue().get(getKey(streamKey));
        return result != null ? Long.parseLong(result) : 0L;
    }

    public void reset(String streamKey) {
        stringRedisTemplate.delete(getKey(streamKey));
    }

    private String getKey(String streamKey) {
        return REDIS_LIKE_PREFIX + streamKey;
    }
}
