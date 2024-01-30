    package com.What2Do.What2Do.member.repository;

    import com.What2Do.What2Do.member.domain.Member;

    import org.springframework.data.jpa.repository.EntityGraph;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface MemberRepository extends JpaRepository<Member, Long> {
        Member findById(Member member);
        Member findByUserName(String myUserName);

        Optional<Member> findByEmail(String email);
        Optional<Member> findById(Long id);

    }
