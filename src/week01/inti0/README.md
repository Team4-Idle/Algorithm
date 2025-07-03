## 과거 풀이법 및 주요 메소드
### map.merge
merge(key, 값이 없었을 때 put할 Value, 충돌시 매핑전략)

```java
    default V merge(K key, V value,
            BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = get(key);
        V newValue = (oldValue == null) ? value :
                   remappingFunction.apply(oldValue, value);
        if (newValue == null) {
            remove(key);
        } else {
            put(key, newValue);
        }
        return newValue;
    }
```

### Collectors.toMap(매개변수 3개짜리)
(
    키전달방식,
    밸류전달방식,
    충돌시 매핑전략
)


```java
public static <T, K, U>
    Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
                                    Function<? super T, ? extends U> valueMapper,
                                    BinaryOperator<U> mergeFunction) {
        return toMap(keyMapper, valueMapper, mergeFunction, HashMap::new);
    }
```


```java
import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<String, Integer> map = IntStream.range(0, keymap.length)
            .mapToObj(i -> IntStream.range(0, keymap[i].length())
                .boxed()
                .collect(Collectors.toMap(
                    j -> keymap[i].charAt(j) + "",
                    j -> j + 1,
                    (v1, v2) -> Math.min(v1, v2))
                ))
            .flatMap(m -> m.entrySet().stream())
            .collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue(),
                (v1, v2) -> Math.min(v1, v2)));
        
        // return IntStream.range(0, targets.length)
        //     .map(i -> Arrays.stream(targets[i].split(""))
        //          .map(s -> map.getOrDefault(s, -1))
        //          .reduce(0, (a, b) -> {
        //              if (a == -1 || b == -1) {
        //                  return -1;
        //              }
        //              return a + b;
        //          }))
        //     .toArray();
        
        return IntStream.range(0, targets.length)
            .mapToObj(i -> Arrays.stream(targets[i].split(""))
                 .map(key -> map.getOrDefault(key, -1)))
            .mapToInt(stream -> stream.reduce(
                0, (a, b) -> {
                    if (a == -1 || b == -1) {
                        return -1;
                    }
                    return a + b;
                }))
            .toArray();
    }
}

```
