package backend.project.service.implementation;

import backend.project.dto.EditItemRequest;
import backend.project.dto.ItemDTO;
import backend.project.entity.Item;
import backend.project.exception.ItemNotFoundException;
import backend.project.exception.UserNotFoundException;
import backend.project.repository.ItemRepository;
import backend.project.repository.UserRepository;
import backend.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public ItemDTO getItemById(int id) {
        var optionalUser = itemRepository.findById(id);
        var item = optionalUser.orElseThrow(
                () -> new ItemNotFoundException("Item " + id + " does not exist"));
        return modelMapper.map(item, ItemDTO.class);
    }

    @Override
    public List<ItemDTO> getItems(String username) {
        var userOptional = userRepository.findUserByUsername(username);
        var user = userOptional.orElseThrow(() ->
                new UserNotFoundException("ОК"));
        List<ItemDTO> listB = user.getItems().stream()
                .map(item -> modelMapper.map(item,ItemDTO.class))  // В этой функции происходит преобразование типов
                .collect(Collectors.toList());

        return         listB;
    }



    @Override
    @Transactional
    public ItemDTO addItem(ItemDTO itemDto) {
        var item = modelMapper.map(itemDto, Item.class);
        if (item.getUser() != null)
            item.getUser().getItems().add(item);

        itemRepository.save(item);
        return itemDto;
    }



    @Override
    @Transactional
    public ItemDTO updateItem(EditItemRequest request) {

        var itemOptional = itemRepository.findById(request.getId());
        var existingItem = itemOptional.orElseThrow(() ->
                new ItemNotFoundException("Item" + request.getId() + " not found"));

        existingItem.setItem_name(request.getItem_name());
        existingItem.setSrc_img(request.getSrc_img());
        existingItem.setDescription(request.getDescription());
        if (existingItem.getUser() != null){
            var userOptional = userRepository.findUserByUsername(existingItem.getUser().getUsername());
            var existingUser = userOptional.orElseThrow(() ->
                    new UserNotFoundException("User " + existingItem.getUser().getUsername() + " not found"));

            existingUser.getItems().add(existingItem);
            userRepository.save(existingUser);


            itemRepository.save(existingItem);
        }

        itemRepository.save(existingItem);
        return modelMapper.map(existingItem,ItemDTO.class);
    }


}
