package com.example.acl.legacy;
import java.util.List;
public record LegacyOrderResponse(String id, int status, java.util.List<LegacyOrderItem> items) {}