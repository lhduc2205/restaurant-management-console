package cores.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {
    private final ModelMapper mapper;

    public MapperUtil(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
