package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.city.CityFactory;
import cn.mxz.dogz.DogzFactory;

/**
 * 赠送神兽
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:adddogz")

public class AddDogz implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		int typeId = new Integer(args[0]);

		String uname = args[1];

		DogzTemplet fighterTemplet = DogzTempletConfig.get(typeId);

		if(fighterTemplet == null) {

			throw new CommandException("神兽不存在:" + typeId);
		}

		DogzFactory.createNewDogz(CityFactory.getCity(uname), typeId);
	}
}
