package com.example.AuthoRasa.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weight_table")
public class WeightUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="time_taken")
    private LocalDateTime time_taken;

    @Column(name="weight")
    private float weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(LocalDateTime time_taken) {
        this.time_taken = time_taken;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
