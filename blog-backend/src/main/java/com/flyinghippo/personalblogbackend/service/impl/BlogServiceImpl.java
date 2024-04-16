package com.flyinghippo.personalblogbackend.service.impl;

import com.flyinghippo.personalblogbackend.entity.Blog;
import com.flyinghippo.personalblogbackend.mapper.BlogMapper;
import com.flyinghippo.personalblogbackend.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FlyHippo
 * @since 2024-04-16
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
