package com.wd.health.contract;


import com.wd.health.model.Banner;
import com.wd.health.model.Data;
import com.wd.health.model.Department;
import com.wd.health.model.DiseaseCategory;
import com.wd.health.model.DiseaseKnowledge;
import com.wd.health.model.DrugsCategoryList;
import com.wd.health.model.DrugsKnowledge;
import com.wd.health.model.DrugsKnowledgeList;
import com.wd.health.model.PopularSearch;
import com.wd.health.model.Search;
import com.wd.health.model.ShowCircleBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/*时间:2020/1/8
创建人:yang 
创建人:杨靖宇*/
public interface IRequset {

    @GET("share/v1/bannersShow")
    Observable<Data<List<Banner>>> banner();

    //首页搜索
    @GET("share/v1/homePageSearch")
    Observable<Data<Search>>  homePageSearch(@Query("keyWord") String keyWord);

    //查询科室列表
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Data<List<Department>>> findDepartmentd();
    //根据科室查询对应病症
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Data<List<DiseaseCategory>>>findDiseaseCategory(@Query("departmentId")int departmentId);

    //查询科室的列表
     @GET("share/knowledgeBase/v1/findDepartment")
     Observable<Data<List<InquiryBean>>>    findDepartment();

    //病友圈列表展示
    @GET("user/sickCircle/v1/findSickCircleList")
    Observable<Data<List<ShowCircleBean>>> checkCirclelist(@QueryMap Map<String,String> querymap);
    //查询健康资讯板块
    @GET("share/information/v1/findInformationPlateList")
    Observable<Data<List<ZixunBean>>>   findInformationList();

    //根据资讯板块查询资讯列表
    @GET("share/information/v1/findInformationList")
    Observable<Data<List<XiangqingBase>>>   xiangqingzixun(@Query("plateId")int plateId,@Query("page")int page,@Query("count")int count);

    //药品科目分类列表查询
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Data<List<DrugsCategoryList>>>findDrugsCategoryList();
    //根据药品类目查询常见药品
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Data<List<DrugsKnowledgeList>>>findDrugsKnowledgeList(@Query("drugsCategoryId") int drugsCategoryId,@Query("page")int page,@Query("count")int count);
    //查询常见病症详情
    @GET("share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<Data<DiseaseKnowledge>>findDiseaseKnowledge(@Query("id")int id);
    //查询常见药品详情
    @GET("share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<Data<DrugsKnowledge>>findDrugsKnowledge(@Query("id")int id);
    //热门搜索
    @GET("share/v1/popularSearch")
    Observable<Data<List<PopularSearch>>> popularSearch();
}
