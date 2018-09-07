package site.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import site.common.service.impl.IndvInfoVO;

public interface CommonIndvInfoInqHistService {

	public void insertIndvInfoInqHist(Map param) throws Exception;
}
