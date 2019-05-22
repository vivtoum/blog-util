package io.github.vivtoum.blog.api.user.feign;

import io.github.vivtoum.blog.api.common.feign.BaseFeign;
import io.github.vivtoum.blog.api.user.vo.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @version 0.0.1
 * @author YT.Hu
 * @date 2019/4/17 0:54
 */

@FeignClient(name = "blog-svc", path = "/user/", url = "${blog-svc.url}")
public interface UserFeign extends BaseFeign<UserVo> {

}
