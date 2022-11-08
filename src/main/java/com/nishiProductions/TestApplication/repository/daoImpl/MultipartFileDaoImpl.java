//package com.nishiProductions.TestApplication.repository.daoImpl;
//
//import com.nishiProductions.TestApplication.model.entity.MultipartFile;
//import com.nishiProductions.TestApplication.model.entity.User;
//import com.nishiProductions.TestApplication.repository.dao.MultipartFileDao;
//import com.nishiProductions.TestApplication.repository.dao.UserDao;
//import com.nishiProductions.TestApplication.repository.dto.MultipartFileDto;
//import com.nishiProductions.TestApplication.repository.transformer.MultipartFileTransformer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//
//@Slf4j
//@Repository
//public class MultipartFileDaoImpl  extends BaseDaoImpl<MultipartFile> implements MultipartFileDao {
//
//    MultipartFileTransformer multipartFileTransformer;
//
//    @Override
//    public MultipartFileDto saveMultipartFile(MultipartFileDto multipartFileDto)
//    {
//        log.info("MultipartFileDaoImpl.saveMultipartFile() invoked.");
//        MultipartFile multipartFile = multipartFileTransformer.transformDtoToEntity(multipartFileDto);
//        MultipartFile saveMultipartFile = saveOrUpdate(multipartFile);
//        return multipartFileTransformer.transformEntityToDto(saveMultipartFile);
//    }
//
//}
