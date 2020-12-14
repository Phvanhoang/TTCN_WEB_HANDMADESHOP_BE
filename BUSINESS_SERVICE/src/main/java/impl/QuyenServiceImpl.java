package impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import model.DacQuyen;
import model.GioiTinh;
import model.TrangThaiDonHang;
import repository.QuyenRepository;
import service.GioiTinhService;
import service.QuyenService;
import service.TrangThaiDonHangService;

import javax.annotation.PostConstruct;
@Service
public class QuyenServiceImpl implements QuyenService {
	@Autowired
	private QuyenRepository quyenRepository;

	public ArrayList<DacQuyen> getDanhSachDacQuyen() {
		return quyenRepository.findAll();
	}

	public void save(DacQuyen dacQuyen) {
		quyenRepository.save(dacQuyen);
	}

	public boolean checkTenDacQuyen(String tenDacQuyen) {
		DacQuyen dacQuyen = quyenRepository.findByTenDacQuyen(tenDacQuyen);
		if (dacQuyen == null) return false;
		return true;
	}

}
