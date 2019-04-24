package com.scda.security.test.feign;

import com.scda.business.common.vo.demo.DemoVo;
import com.scda.common.response.ResponseVo;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: liuqi
 * @Date: 2019/4/23 17:47
 * @Description:服务调用服务示例
 */
@FeignClient(value = "demo", fallbackFactory = DemoFeign.DemoFeignFallback.class)
public interface DemoFeign {
    @PostMapping(value = "/demo/example/list")
    ResponseVo list(@RequestBody DemoVo demoVo);

    /**
     * 降级方案
     */
    @Component
    class DemoFeignFallback implements FallbackFactory<DemoFeign> {
        @Override
        public DemoFeign create(Throwable throwable) {
            return new DemoFeign() {
                @Override
                public ResponseVo list(DemoVo demoVo) {
                    return ResponseVo.busy(null);
                }
            };
        }
    }
}
