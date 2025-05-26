package net.dotevolve.base.data;

import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component
public class CreatedAtComparator implements Comparator<BaseEntity> {
    @Override
    public int compare(BaseEntity o1, BaseEntity o2) {
        return o1.getMetaData().getCreatedAt().compareTo(o2.getMetaData().getCreatedAt());
    }

}
