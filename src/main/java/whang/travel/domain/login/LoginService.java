package whang.travel.domain.login;

import whang.travel.domain.member.Member;

public interface LoginService {

    Member Login(String loginId, String password);
}
