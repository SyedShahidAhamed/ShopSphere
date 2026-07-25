package com.shahid.shopsphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@Column(nullable=false)
private String name;
@Column(nullable=false,unique=true)
private String email;
@Column(nullable=false)
private String password;
@Enumerated(EnumType.STRING)
private Role role;
}
/*
"Why shouldn't we use @Data on JPA entities?"

A solid answer is:

"@Data generates equals(), hashCode(), and toString() automatically. In JPA entities, these methods can cause issues with lazy loading, entity identity, and persistence behavior. That's why many production projects prefer @Getter and 
@Setter with only the additional methods they explicitly need."
📖 Let's understand every annotation
@Entity
@Entity


This tells Hibernate:

"Create a table in the database for this class."

Without it, Hibernate ignores the class.

@Table
@Table(name = "users")

By default, Hibernate would create a table named user.

We explicitly name it users to keep the database naming clear and avoid conflicts with SQL keywords.

@Id
@Id

Marks the primary key.

Every entity must have exactly one primary key.

@GeneratedValue
@GeneratedValue(strategy = GenerationType.IDENTITY)

This tells MySQL to auto-increment the ID.

Example:

1
2
3
4
...

You don't set the ID manually.

@Column(nullable = false)
@Column(nullable = false)

This means the column cannot contain NULL.

If someone tries to save a user without a name, the database will reject it.

unique = true
@Column(unique = true)

Email addresses must be unique.

✔ shahid@gmail.com
✘ shahid@gmail.com

The second insert will fail because the email already exists.

@Enumerated(EnumType.STRING)

Our Role enum contains:

USER
ADMIN

If we don't specify EnumType.STRING, JPA stores:

0
1

If we use EnumType.STRING, it stores:

USER
ADMIN

This makes the database much easier to read and maintain.


 */