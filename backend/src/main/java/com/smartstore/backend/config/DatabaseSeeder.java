package com.smartstore.backend.config;

import com.github.javafaker.Faker;
import com.smartstore.backend.domain.model.User;
import com.smartstore.backend.domain.model.Post;
import com.smartstore.backend.domain.port.UserRepository;
import com.smartstore.backend.domain.port.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner seedDatabase(UserRepository userRepository, PostRepository postRepository) {
        return args -> {
            Faker faker = new Faker(Locale.ENGLISH);

            // Known static users
            List<User> staticUsers = List.of(
                    new User("user1@example.com", "Allison Rice", "https://placeimg.com/640/480/people"),
                    new User("user2@example.com", "Nathaniel Marsh", "https://placeimg.com/640/480/people"),
                    new User("user3@example.com", "Chloe Lewis", "https://placeimg.com/640/480/people"),
                    new User("user4@example.com", "Leo Bennett", "https://placeimg.com/640/480/people"),
                    new User("user5@example.com", "Amelia Davis", "https://placeimg.com/640/480/people")
            );

            for (User user : staticUsers) {
                userRepository.save(user);
            }

            List<Post> staticPosts = List.of(
                    new Post(null, "Coach option into thank value.", "Improve right inside meet.", "user1@example.com", LocalDateTime.of(2025, 4, 17, 23, 3, 49)),
                    new Post(null, "Market many tell agreement Congress thing glass.", "Worry read can price. Spring prove soldier group respond business tend.", "user1@example.com", LocalDateTime.of(2025, 4, 6, 23, 3, 49)),
                    new Post(null, "By condition themselves employee cell least.", "Partner age certain form study police home. Create tonight themselves.", "user1@example.com", LocalDateTime.of(2025, 4, 21, 23, 3, 49)),
                    new Post(null, "Mother newspaper animal thousand cover field truth.", "Benefit kitchen PM maybe cause suffer. My teacher general moment behavior forget business season.", "user1@example.com", LocalDateTime.of(2025, 4, 2, 23, 3, 49)),
                    new Post(null, "Situation level almost work house those condition.", "Enjoy five expert job key network answer knowledge. Street include fund.", "user1@example.com", LocalDateTime.of(2025, 4, 15, 23, 3, 49)),
                    new Post(null, "Nothing lead fight majority affect report manager.", "Speech mother popular someone fear rock great. Interest develop suggest decide ability plan.", "user2@example.com", LocalDateTime.of(2025, 4, 10, 14, 20, 35)),
                    new Post(null, "Garden reduce sister nearly whether music peace.", "Community article art yard today. Power police some purpose establish.", "user3@example.com", LocalDateTime.of(2025, 4, 3, 10, 5, 20)),
                    new Post(null, "Evening run size back strategy.", "Yourself blood view gas pass history. Include goal strong discussion.", "user4@example.com", LocalDateTime.of(2025, 4, 22, 8, 15, 0)),
                    new Post(null, "Describe defense bag sit cold opportunity.", "Beat eat future himself suggest real event deal chair.", "user5@example.com", LocalDateTime.of(2025, 4, 13, 16, 45, 18)),
                    new Post(null, "Remain voice event share speech language environment.", "Make even open general thing ready kind. Place four alone another.", "user5@example.com", LocalDateTime.of(2025, 4, 19, 12, 30, 10))
            );

            for (Post post : staticPosts) {
                postRepository.save(post);
            }

            // Faker-generated users and posts
            IntStream.rangeClosed(6, 10).forEach(i -> {
                String email = "fakeuser" + i + "@example.com";
                User user = new User(email, faker.name().fullName(), faker.internet().avatar());
                userRepository.save(user);

                IntStream.rangeClosed(1, 10).forEach(j -> {
                    Post post = new Post(
                            null,
                            faker.book().title(),
                            faker.lorem().paragraph(3),
                            email,
                            LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30))
                    );
                    postRepository.save(post);
                });
            });

            System.out.println("Seeded static + Faker users and posts into H2.");
        };
    }
}
