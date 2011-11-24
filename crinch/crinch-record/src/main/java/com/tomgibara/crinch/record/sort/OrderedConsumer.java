package com.tomgibara.crinch.record.sort;

import java.io.File;
import java.util.List;

import com.tomgibara.crinch.record.LinearRecord;
import com.tomgibara.crinch.record.ProcessContext;
import com.tomgibara.crinch.record.RecordConsumer;
import com.tomgibara.crinch.record.def.ColumnType;
import com.tomgibara.crinch.record.def.RecordDefinition;
import com.tomgibara.crinch.record.def.SubRecordDefinition;
import com.tomgibara.crinch.record.dynamic.DynamicRecordFactory;

public abstract class OrderedConsumer implements RecordConsumer<LinearRecord> {

	private final SubRecordDefinition subRecDef;
	
	ProcessContext context;
	RecordDefinition definition;
	DynamicRecordFactory factory;

	public OrderedConsumer(SubRecordDefinition subRecDef) {
		this.subRecDef = subRecDef;
	}
	
	@Override
	public void prepare(ProcessContext context) {
		this.context = context;
		List<ColumnType> types = context.getColumnTypes();
		if (types == null) throw new IllegalStateException("no types");
		definition = RecordDefinition.fromTypes(context.getColumnTypes()).build().withOrdering(context.getColumnOrders()).asBasis();
		if (subRecDef != null) definition = definition.asSubRecord(subRecDef);
	}

	@Override
	public void beginPass() {
		factory = DynamicRecordFactory.getInstance(definition);
	}
	
	File sortedFile(boolean input) {
		return new File(context.getOutputDir(), context.getDataName() + ".compact." + (input ? definition.getBasis().getId() : definition.getId()));
	}
	

}
