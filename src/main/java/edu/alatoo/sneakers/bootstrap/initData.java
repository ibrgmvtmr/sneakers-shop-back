package edu.alatoo.sneakers.bootstrap;

import edu.alatoo.sneakers.model.Item;
import edu.alatoo.sneakers.model.User;
import edu.alatoo.sneakers.model.enums.Roles;
import edu.alatoo.sneakers.repository.ItemRepository;
import edu.alatoo.sneakers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class initData implements CommandLineRunner {
    @Override
    public void run(String... args) {
        generateUsers();
        generateItems();
    }

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    private void generateUsers() {
        User user1 = new User("firstName1", "lastName1", "user1", "user1@example.com", "password1", Set.of());
        User user2 = new User("firstName2", "lastName2", "user2", "user2@example.com", "password2", Set.of());
        User user3 = new User("firstName3", "lastName3", "user3", "user3@example.com", "password3", Set.of());

        userRepository.saveAll(List.of(user1, user2, user3));
    }

    private void generateItems() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            User user = users.get(0);

            Item item1 = new Item("itemName1", "label1", 100L, user);
            Item item2 = new Item("itemName2", "label2", 200L, user);
            Item item3 = new Item("itemName3", "label3", 300L, user);

            itemRepository.saveAll(List.of(item1, item2, item3));
        }
    }
}
