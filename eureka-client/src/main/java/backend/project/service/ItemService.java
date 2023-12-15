package backend.project.service;

import backend.project.dto.EditItemRequest;
import backend.project.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO getItemById(int id);
    List<ItemDTO> getItems(String username);

    ItemDTO addItem(ItemDTO itemDTO);
    ItemDTO updateItem(EditItemRequest EditItemRequest);

}
