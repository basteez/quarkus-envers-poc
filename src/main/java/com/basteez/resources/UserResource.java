package com.basteez.resources;

import com.basteez.model.User;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface UserResource extends PanacheEntityResource<User, String> {
}
