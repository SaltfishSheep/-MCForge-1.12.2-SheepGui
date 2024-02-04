package saltsheep.guilib.client;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import saltsheep.lib.common.BaseType;
import saltsheep.lib.common.ValueContainer;
import saltsheep.lib.exception.InputIllegalException;
import saltsheep.lib.network.NetworkHelper;

public abstract class ContainerAuto extends Container {
	
	private Map<String,ValueContainer> autoValue = Maps.newHashMap();
	
	@Nonnull
	public <T> T getValue(BaseType<T> type,String valueName) throws InputIllegalException {
		if(autoValue.containsKey(valueName)) {
			ValueContainer container = autoValue.get(valueName);
			if(container.type!=type)
				throw new InputIllegalException();
			else
				return container.getValue(type);
		}else {
			return type.defalut();
		}
	}
	
	public <T> void putValue(BaseType<T> type,String valueName,T value) throws InputIllegalException {
		if(autoValue.containsKey(valueName)) {
			ValueContainer container = autoValue.get(valueName);
			if(container.type!=type)
				throw new InputIllegalException();
			container.markDirty();
			container.setValue(type, value);
		}else {
			ValueContainer container = ValueContainer.create(type, value);
			container.markDirty();
			autoValue.put(valueName, container);
		}
	}
	
	@Nullable
	public BaseType<?> getValueType(String valueName) {
		if(autoValue.containsKey(valueName))
			return autoValue.get(valueName).type;
		return null;
	}
	
	public <T> void resetValueTo(BaseType<T> type,String valueName) {
		autoValue.remove(valueName);
		ValueContainer container = ValueContainer.create(type);
		container.markDirty();
		autoValue.put(valueName, container);
	}
	
	public <T> void resetValueTo(BaseType<T> type,String valueName,T value) {
		autoValue.remove(valueName);
		ValueContainer container = ValueContainer.create(type,value);
		container.markDirty();
		autoValue.put(valueName, container);
	}
	
	public void writeUpdate(ByteBuf buf) {
		for(Entry<String, ValueContainer> each:this.autoValue.entrySet()) {
			if(each.getValue().isDirty()) {
				buf.writeBoolean(true);
				NetworkHelper.writeString(buf, each.getKey());
				ValueContainer.write(buf, each.getValue());
			}
		}
		buf.writeBoolean(false);
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	public <T> void readUpdate(ByteBuf buf) throws InputIllegalException {
		while(buf.readBoolean()) {
			String valueName = NetworkHelper.readString(buf);
			ValueContainer container = ValueContainer.read(buf);
			this.putValue((BaseType<T>) container.type, valueName, (T) container.getValue(container.type));
		}
	}

}
