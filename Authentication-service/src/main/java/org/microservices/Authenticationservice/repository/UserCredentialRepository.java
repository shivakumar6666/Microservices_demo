package org.microservices.Authenticationservice.repository;

import org.microservices.Authenticationservice.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {


}
