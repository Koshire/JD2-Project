package utils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Iterator;
import java.util.List;

public class PredicateBuilder {

    public static Predicate build(List<Predicate> predicate) {
        Iterator<Predicate> iter = predicate.listIterator();
        BooleanBuilder pr = new BooleanBuilder();
        while (iter.hasNext()) {
            pr.and(iter.next());
        }
        return pr;
    }
}
