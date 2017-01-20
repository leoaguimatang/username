package com.leo.service;

import com.leo.exception.ErrorKey;
import com.leo.exception.UsernameException;
import com.leo.model.Dictionary;
import com.leo.model.Result;
import com.leo.model.User;
import com.leo.repository.DictionaryRepository;
import com.leo.repository.UserRepository;
import com.leo.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DictionaryRepository dictionaryRepository;

    private static final String[] adjectives = {
        "aged", "ancient", "autumn", "billowing", "bitter", "black", "blue", "bold",
        "broad", "broken", "calm", "cold", "cool", "crimson", "curly", "damp",
        "dark", "dawn", "delicate", "divine", "dry", "empty", "falling", "fancy",
        "flat", "floral", "fragrant", "frosty", "gentle", "green", "hidden", "holy",
        "icy", "jolly", "late", "lingering", "little", "lively", "long", "lucky",
        "misty", "morning", "muddy", "mute", "nameless", "noisy", "odd", "old",
        "orange", "patient", "plain", "polished", "proud", "purple", "quiet", "rapid",
        "raspy", "red", "restless", "rough", "round", "royal", "shiny", "shrill",
        "shy", "silent", "small", "snowy", "soft", "solitary", "sparkling", "spring",
        "square", "steep", "still", "summer", "super", "sweet", "throbbing", "tight",
        "tiny", "twilight", "wandering", "weathered", "white", "wild", "winter", "wispy",
        "withered", "yellow", "young"
    };

    private List<String> words;

    /**
     * Initialises the service.
     */
    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * Cache the dictionary words.
     */
    @Scheduled(fixedRateString = "${refresh.delay:1800000}")
    protected void refresh() {
        List<Dictionary> dictionaries = dictionaryRepository.findAll();

        synchronized (this) {
            words = dictionaries.stream().map(dictionary -> dictionary.getWord()).collect(Collectors.toList());
        }
    }

    /**
     * Gets the user details by id
     */
    public User getUserById(Long id) {
        User user = userRepository.findOne(id);
        return user;
    }

    /**
     * Gets the user details by username
     */
    public List<User> searchUsername(String search) {
        List<User> users = userRepository.getUsersByUsername(search);
        return users;
    }

    /**
     * Checks username availability
     */
    public Result checkUsername(String username) throws Exception {
        Result result = containsExplicitWord(username);
        String name = result.getSuggestions().get(0);
        if (StringUtils.isEmpty(name)) {
            throw new UsernameException(ErrorKey.USERNAME_EXPLICIT);
        }

        result.setSuggestions(new ArrayList<>());
        if (result.getValid()) {
            result.setValid(!nameTaken(name));
        }

        if (!result.getValid()) {
            result.setSuggestions(generateNames(14, name));
        }

        return result;
    }

    /**
     * Creates the {@link User}
     */
    @Transactional
    public Result createUser(UserResource userResource) throws Exception {
        Result result = checkUsername(userResource.getUsername());
        if (result.getValid()) {
            // Simulate adding username to user table.
            User user = userResource.to(new User());
            userRepository.save(user);
        }

        return result;
    }

    /**
     * Generates name suggestions
     */
    public List<String> generateNames(int seed, String name) {
        List<String> suggestions = new ArrayList<>();

        Random random = new Random();

        int length = adjectives.length;
        if (length <= 0) return new ArrayList<>();

        do {
            if (name.length() > 0 && name.length() < 6) {
                name += name;
            }
        } while (name.length() < 6);

        for (int i = 1; i <= seed; i++) {
            String suggestion = name.concat("" + i);
            if (nameTaken(suggestion)) {
                for (int x = 0; x < 3; x++) {
                    suggestion = name.concat(name);
                    if (!nameTaken(suggestion) && !suggestions.contains(suggestion)) {
                        break;
                    } else {
                        suggestion = adjectives[random.nextInt(length)] + "_" + name;
                        if (!nameTaken(suggestion)) {
                            break;
                        }
                    }
                }
            }

            if (!StringUtils.isEmpty(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // Sort suggestions
        Collections.sort(suggestions, (a, b) -> a.compareTo(b));

        return suggestions;
    }

    public boolean nameTaken(String name) {
        return userRepository.getByUsername(name) != null;
    }

    public Result containsExplicitWord(String username) {
        Result result = new Result();
        result.setSuggestions(new ArrayList<>());
        String newName = cleanse(username);
        result.setValid(newName.equals(username));
        result.getSuggestions().add(newName);
        return result;
    }

    private String cleanse(String username) {
        for(String word : words) {
            if (username.toLowerCase().contains(word.toLowerCase())) {
                username = username.toLowerCase().replaceAll(word.toLowerCase(), "");
            }
        }

        return username;
    }
}