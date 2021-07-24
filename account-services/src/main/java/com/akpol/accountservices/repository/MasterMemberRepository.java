package com.akpol.accountservices.repository;

import com.akpol.commons.model.MasterMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterMemberRepository extends JpaRepository<MasterMember, Long> {
    MasterMember findByIdEquals(Long Id);
    MasterMember findByFirstNameEquals(String firstName);
    MasterMember findByLastNameEquals(String lastName);
    List<MasterMember> findAllByActiveTrue();
    MasterMember findByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
