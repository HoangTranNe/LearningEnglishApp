package edu.huflit.learning_english.databinding;

import androidx.databinding.MergedDataBinderMapper;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new edu.huflit.learning_english.DataBinderMapperImpl());
  }
}
