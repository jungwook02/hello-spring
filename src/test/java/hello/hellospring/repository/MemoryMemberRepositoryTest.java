package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {


     MemoryMemberRepository repository = new MemoryMemberRepository();

    // test가 끝나고 메모리를 비워줄 메소드(test는 서로 의존관계가 있으면 안됨!)
    @AfterEach
    public void afterEach() {
        repository.clearStore();


    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");


        repository.save(member);
        // Optional 형식은 get으로 받을 수 있음
        Member result = repository.findByID(member.getId()).get();
        // 최근에는 이게 더 많이 쓰임 -> member = result랑 같다는 의미
        assertThat(member).isEqualTo(result);
        //System.out.println("result = "+ (result == member));
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring2");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);


    }
}
