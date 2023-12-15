package backend.project.config;

import backend.project.dto.ItemDTO;
import backend.project.exception.UserNotFoundException;
import backend.project.entity.Item;
import backend.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(Item.class, ItemDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getUser().getId(), ItemDTO::setUserId));

        modelMapper.createTypeMap(ItemDTO.class, Item.class);

        modelMapper.typeMap(ItemDTO.class, Item.class)
                .addMappings(mapper -> mapper.using(ctx -> {
                    if (ctx.getSource() != null){
                        Integer userId = (Integer) ctx.getSource();
                        var userOptional = userRepository.findById(userId);
                        return userOptional.orElseThrow(() ->
                                new UserNotFoundException("User" + userId + " not found"));
                    }
                    else {
                        return null;
                    }

                }).map(ItemDTO::getUserId, Item::setUser));

        return modelMapper;
    }
}
