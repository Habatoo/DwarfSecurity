package dwarf.framework.model;

import lombok.*;
import javax.persistence.*;

/**
 * Модель пользователя. Записывается в БД в таблицу с имененм users.
 * @version 0.001
 * @author habatoo
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USR")
@ToString(of = {"username"})
@EqualsAndHashCode(of = {"userId"})
public class User {
    private static final long serialVersionUID = -1399500801576919731L;

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Column(
            name="USER_ID",
            updatable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Integer userId;

    @Column(
            name="USER_NAME",
            length = 100,
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String username;

    /**
     * Конструктор для создания пользователя.
     * @param username - имя пользователя - предпоалагается строковоя переменная Имя + Фамилия.
     * activationStatus - поле подтверждения email пользователя.
     * userStatus - поле статуса пользователя на возможность чтения и записи и доступа к аккаунту
     *
     */
    public User(String username) {
        this.username = username;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (userId == null) {
            if (user.userId != null)
                return false;
        } else if (!userId.equals(user.userId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
