package backend.project.controller;

import backend.project.dto.EditItemRequest;
import backend.project.dto.ItemDTO;
import backend.project.dto.ItemIdResponse;
import backend.project.dto.UsernameRequest;
import backend.project.exception.*;
import backend.project.service.ItemService;
import backend.project.service.UserService;
import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final UserService userService;

    @PostMapping("/get_item_by_id")
    @JsonView(Views.BasicView.class)
    public ResponseEntity<ItemDTO> getItemById(@RequestBody ItemIdResponse idRequest) {
        int id = idRequest.getId();
           return ResponseEntity.ok(itemService.getItemById(id));
    }
    @PostMapping("/get_items_by_username")
    @JsonView(Views.BasicView.class)
    public ResponseEntity<List<ItemDTO>> getItemsByUsername(@RequestBody UsernameRequest request) {
        String username = request.getUsername();
        return ResponseEntity.ok(itemService.getItems(username));
    }

    @PostMapping("/create_item")
    @JsonView(Views.ItemView.class)
    public ResponseEntity<ItemDTO> createItem(
            @RequestBody @Valid ItemDTO itemDto) throws ItemNotCreatedException{

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemService.addItem(itemDto));
    }

    @PutMapping("/update_item")
    @JsonView(Views.ItemView.class)
    public ResponseEntity<ItemDTO> updateItem(
            @RequestBody @Valid EditItemRequest request) throws ItemNotUpdatedException{
        itemService.updateItem(request);

        return ResponseEntity.ok(itemService.updateItem(request));
    }
}
