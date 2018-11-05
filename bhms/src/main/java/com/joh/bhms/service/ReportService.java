package com.joh.bhms.service;

import java.util.List;

import com.joh.bhms.domain.model.NotificationD;

public interface ReportService {

	List<NotificationD> findAdminNotifications();

	List<String> findAllChronicDisease();

}
