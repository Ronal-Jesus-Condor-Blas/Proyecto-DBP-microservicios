package com.proyecto_dbp.proyecto_dbp.user.domain;

import com.proyecto_dbp.proyecto_dbp.comment.domain.Comment;
import com.proyecto_dbp.proyecto_dbp.foodrating.domain.FoodRating;
import com.proyecto_dbp.proyecto_dbp.post.domain.Post;
import com.proyecto_dbp.proyecto_dbp.restaurantrating.domain.RestaurantRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Identificador único del usuario

    @NotNull
    @Size(max = 50)
    private String name; // Nombre del usuario

    @NotNull
    @Email
    private String email; // Correo electrónico del usuario

    @NotNull
    private String password; // Contraseña del usuario

    private String profilePicture; // URL de la foto de perfil

    private String biography; // Biografía del usuario

    @Enumerated(EnumType.STRING)
    private UserType userType; // Tipo de usuario (consumidor/influencer)

    private LocalDateTime registerDate; // Fecha y hora de registro

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Post> posts;  // Un usuario puede crear muchos posts

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> comments;  // Un usuario puede hacer muchos comentarios

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    ) // Tabla intermedia para la relación muchos a muchos entre User y User

    private Set<User> followers;  // Usuarios que siguen a este usuario

    @ManyToMany(mappedBy = "likedBy")
    private Set<Post> likedPosts; // Posts que le gustan a este usuario

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<FoodRating> foodRatings;  // Un usuario puede calificar muchos platos

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<RestaurantRating> restaurantRatings;  // Un usuario puede calificar muchos restaurantes
}
