    package com.What2Do.What2Do.member.service;


    import com.What2Do.What2Do.member.domain.Member;
    import com.What2Do.What2Do.member.domain.Role;
    import com.What2Do.What2Do.member.etc.MemberRequestDto;
    import com.What2Do.What2Do.member.repository.MemberRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import javax.persistence.EntityNotFoundException;
    import javax.transaction.Transactional;
    import java.util.*;

    @Service
    @Transactional
    public class MemberService implements UserDetailsService {

        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;


        public void create (MemberRequestDto memberRequestDto) throws Exception {

            if (memberRepository.findByEmail(memberRequestDto.getEmail()).isPresent()) {
                throw new Exception("이미 존재하는 이메일입니다.");
            }

            if (memberRepository.findByUserName(memberRequestDto.getUserName()) != null) {
                throw new Exception("이미 존재하는 닉네임입니다.");
            }

            Member member1 = Member.builder()
                    .userName(memberRequestDto.getUserName())
                    .password(memberRequestDto.getPassword())
                    .name(memberRequestDto.getName())
                    .age(memberRequestDto.getAge())
                    .address(memberRequestDto.getAddress())
                    .mobileNumber(memberRequestDto.getMobileNumber())
                    .job(memberRequestDto.getJob())
                    .email(memberRequestDto.getEmail())
                    .role(Role.ROLE_USER)
                    .build();
            member1.setPassword(passwordEncoder.encode(member1.getPassword()));
            memberRepository.save(member1);
        }


        public void update (MemberRequestDto memberRequestDto) throws Exception {
            Member member2 = memberRepository.findById(Long.parseLong(memberRequestDto.getId())).orElseThrow(Exception::new);
            member2.updateMember(memberRequestDto.getMobileNumber(), memberRequestDto.getJob(), memberRequestDto.getEmail());
            memberRepository.save(member2);
        }

        public  Member findById(Long myId) throws EntityNotFoundException {
            Member members = memberRepository.findById(myId).orElseThrow(EntityNotFoundException::new);
            return members;
        }


        //        doLogin 이라는 spring 내장 메서드가 실행 될 때, UserDetailsService 를 구현한 클래스의
        //        loadByUsername 이라는 메서드를 찾는 걸로 약속

        //    String username 은 사용자가 화면에 입력한 email 주소를 받아서
        //    loadUserByUsername 에 넣어준다.


        //나의 원래 로그인 서비스
        @Override
        public UserDetails loadUserByUsername(String myUserName) throws UsernameNotFoundException {

    //        doLogin 내장 기능이 정상 실행되려면, DB 에서 조회한 id/pw 를 return 해줘야 한다.
            Member member  = memberRepository.findByUserName(myUserName);
            if(member == null) {
                throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + myUserName);
            }
    //        DB 에서 조회한 email, password, 권한을 return. 권한이 없다면 emptyList 로 return
//            return new User(member.getUserName(), member.getPassword(),Collections.emptyList());

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole().getKey()));

            return new User(member.getUserName(), member.getPassword(), authorities);
        }

    }

