package com.scda.common.db.page;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 继承page,使得排序传入java entity字段值也可以，
 * @param <T>
 */
public class BasePage<T> extends Page<T> {
    @Override
    public IPage<T> setAscs(List<String> ascs) {
        return super.setAscs(ascs.stream().map(StrUtil::toUnderlineCase).collect(Collectors.toList()));
    }

    @Override
    public IPage<T> setAsc(String... ascs) {
        return super.setAsc(Arrays.stream(ascs).map(StrUtil::toUnderlineCase).collect(Collectors.joining()));
    }

    @Override
    public IPage<T> setDescs(List<String> descs) {
        return super.setDescs(descs.stream().map(StrUtil::toUnderlineCase).collect(Collectors.toList()));
    }

    @Override
    public IPage<T> setDesc(String... descs) {
        return super.setDesc(Arrays.stream(descs).map(StrUtil::toUnderlineCase).collect(Collectors.joining()));
    }
}
