package com.kwdz.blog.web.controller.rsa;

import com.kwdz.blog.api.common.util.RSAUtils;
import com.kwdz.blog.api.common.util.RsaSecurityParameter;
import com.kwdz.blog.api.user.feign.UserFeign;
import com.kwdz.blog.api.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author YT.Hu
 */
@Slf4j
@Controller
@RequestMapping("/rsa/")
public class RSAController {

    private static final String PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCVgLNU89BZR/hmdlnpTSXFEJ6gBws+ePrfhlT33v5l/H9oenQyuiUf3NGuJDxjgwaajR0RYJWlKhhG8cwEDUM69bDH3c5vow9BsW2OOP3ph92jVIls64XYD86pDkKzZBGzuxSBfqfHffq8zv43l+xZBevQ3g0i0msQqwoVJ1MzFHG/UqAI41c0VUWELn+1yYiWiuX65WI85QaAq+yHzwqiFcQr6n1F3avi/Gyju7kZYz8iz4qI7EzAms4vk3CFKMF0wRvcJZ0RONuQqrgzKUlU0xyoaVc908NINrr+n0VRcJTedhsfAGUF2KLa75+7DrjypdoadmJj+sAnQnwhuKQRAgMBAAECggEAWm72i43MWyVQ2dH/g0N6dGEgQtbf81NFFc7xONr38gMxoqZoAYNeD48IexlKOju40+ZGTgupmbYD524+JblMEK8r10qRpC/ze55zEdPhq9DWvgTV+D/jv5WYQeTxbgg7OQgSWF6f6OmIsYtYdZJ9kkAlrDuVoJm9z+BWggM+radkgB6Hgm6RIRXh0QWkZ2AzrbpptYTbYM8Fvppw1bASX8os2k1DHpUpabZLlrl5hNJQ5R5PHDA4pQumgnL7saWQX74MTT7BN0GPfvBCe+tA/nDRjddAD+tb4TdAUqTE2O9zd12Is1XwshlfETQjgk7aQd34l9mYri7yv36xxIuQwQKBgQDI1iCNc8zYz49kDZXKXRH9YMO498XbgsLYwzDz7N0kk50hTBR8nuf+Y/9BX6ld4AG+12tt55jG6yKwZAxp7cF3/yhUf+CLRo5dUWlTTNTZRpOafQoMHhHGl8w/2HmbilP1exN8eR7iDhfln047r+euGWkOAkR5nwLzVZEvBDEgCQKBgQC+kQeBmi60y4UzoX3a05hq6nfwFblUE1fYMRwMKiMZVmY3BBACPeRkAkzknOBuSQDBNC5Wb4rOw8EGpM3luXWY0LRvv3B34V2zWFGww0ra6ybsnohRxjn1Ut6rdzjdcn0xpfuZxE/E38geg3NvBMbH/pfq/tWolPoro+EXrUfVyQKBgQCXum3Nb/cKtYS3KixIPhghPMGhNE+ejyN1b8HEBubrgznqtfekTskmP5XQZd0mtt2auFTJK3cYkPwcFvnp7V2Esphdfl692ggkKCVScXE81T0eMektTmpPhs8gTQNSpybXBqyqFTdnwQ38xGXuYe6+cSQVGVFyMNehzrtosdoLQQKBgQCG0BzKT+P43P3Hj9h0JTrvcB4XR5cLZn+/nsZjyK957khzZTlaDKT1Jzd0h5KyEo0pIRwi49gD/DIi3BYZDUGnMQZlA76x4EokZTetYAslojOwM1rm1uALsGt8S+R7rNHqfKzolBuGgHWXOJFCGdPyiia5BTzrfTEaDF5iHKD0GQKBgQCkm613G4iTWLlVj2XUpFbjqjBvh/Qv4CVvmTmWRn/x4SLgD99GL/l5czW6eZjiVdycRy8NI03Q+4I6BJj7519MLjyS31geRIxz22hF7M0azsn3lMkPchHNszlpSFQzyw0mM2mTUBeN7UzTzQN1Z/7Z319U6NpVloQLYfjQgNUY5A==";


    @Autowired
    private UserFeign userFeign;

    @GetMapping("/")
    public String index() {
        return "rsa";
    }

    /**
     * RSA测试
     *
     * @return object
     */
    @PostMapping("rsaTest")
    @RsaSecurityParameter
    public UserVo rsaTest(@RequestBody UserVo vo) {
        log.info(vo.getUserId());
        return userFeign.get(RSAUtils.decryptDataOnJava(vo.getUserId(), PRIVATE_KEY)).getData();
    }
}
