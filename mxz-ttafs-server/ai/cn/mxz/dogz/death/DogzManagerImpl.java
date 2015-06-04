package cn.mxz.dogz.death;
//package cn.mxz.dogz;
//
//import interfaces.AdditionMultiplier;
//import interfaces.IPracticeData;
//import interfaces.SpeedUpTemplet;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import lincen.javase.math.Fraction;
//import cn.javaplus.common.util.Util;
//import lincen.javase.util.probability.ProbabilityUtil;
//import lincen.javase.util.time.Time;
//import lincen.javase.util.time.colddown.ColdDown;
//import message.S;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import templets.DogzPracticeExpTemplet;
//import templets.DogzPracticeExpTempletConfig;
//import templets.DogzPracticeGradeTemplet;
//import templets.DogzPracticeGradeTempletConfig;
//import templets.DogzPracticeOpenGridTempletConfig;
//import templets.DogzPracticeSpeedUpTempletConfig;
//import templets.DogzPracticeTimeTempletConfig;
//import templets.DogzQualityTemplet;
//import templets.DogzQualityTempletConfig;
//import templets.DogzSkillTempletConfig;
//import templets.DogzTemplet;
//import templets.DogzTempletConfig;
//import templets.FishTemplet;
//import templets.FishTempletConfig;
//import addition.AdditionMultiplierEmpty;
//import cn.mxz.factory.Factory;
//
//import com.lemon.ai.info.User;
//import com.lemon.ai.info.User.Spirit;
//import com.lemon.ai.main.client.modules.DogzModule;
//import com.lemon.ai.main.debug.Debuger;
//import com.lemon.ai.main.debug.Values;
//import com.lemon.ai.main.error.checkers.CheckerUser;
//import com.lemon.ai.main.logic.dogz.DogzActiveSkill;
//import com.lemon.ai.main.logic.dogz.DogzManager;
//import com.lemon.ai.main.logic.dogz.DogzPassiveSkill;
//import com.lemon.ai.main.logic.dogz.DogzPassiveSkillManager;
//import com.lemon.ai.main.logic.dogz.DogzService;
//import com.lemon.ai.main.logic.dogz.IDogz;
//import com.lemon.ai.main.logic.dogz.Instructor;
//import com.lemon.ai.main.logic.practice.godpractice.IPracticeBean;
//import com.lemon.ai.main.logic.task.base.Task;
//import com.lemon.ai.main.logic.task.normal.TaskNormal;
//import com.lemon.ai.main.message.NormalMessage;
//import com.lemon.ai.main.message.error.ClientError;
//import com.lemon.ai.main.message.error.MaliciousError;
//import com.lemon.ai.main.tasks.runner.MainTasks;
//
//import db.dao.DAO;
//import db.dao.factory.DaoFactory;
//import db.domain.Dogz;
//import db.domain.DogzPractice;
//import define.D;
//import demand.IWares;
//
///**
// * 神兽管理器
// *
// * @author 林岑
// * @time 2013-6-17
// */
//@Component("dogzManager")
//@Scope("prototype")
//public class DogzManagerImpl implements DogzManager<Dogz> {
//
//
//	private Map<Integer, IDogz<Dogz>> dogzs;
//
//	private DogzService<Dogz> service;
//
//	private User user;
//
//	public DogzManagerImpl() {
//		dogzs = new HashMap<Integer, IDogz<Dogz>>();
//	}
//
//	/**
//	 * 所有还能使用的神兽(某些神兽在某个任务完成后, 就自动消失掉)
//	 * @return
//	 */
//	private Map<Integer, IDogz<Dogz>> getAllLives() {
//		Map<Integer, IDogz<Dogz>> dogzs = new HashMap<>(this.dogzs);
//		Iterator<Entry<Integer, IDogz<Dogz>>> it = dogzs.entrySet().iterator();
//
//		TaskNormal ts = getUser().getManager().getNormalTasks();
//
//		while (it.hasNext()) {
//			Entry<Integer, IDogz<Dogz>> next = it.next();
//
//			DogzTemplet temp = DogzTempletConfig.get(next.getValue().getTypeId());
//			Task task = ts.get(temp.getHideTaskID());
//			if(task != null && task.isFinishAll()) {
//				it.remove();
//			}
//		}
//
//		return dogzs;
//	}
//
//	@Override
//	public Iterator<IDogz<Dogz>> iterator() {
//
//		return Lists.newArrayList(getAllLives().values()).iterator();
//	}
//
//	@Override
//	public void init() {
//		initDogzs();
//		//		Debuger.debug("DogzManagerImpl.init()" + this.hashCode() + ":" + this);
//	}
//
//	@SuppressWarnings("unchecked")
//	private void initDogzs() {
//		List<Dogz> all = DaoFactory.getUserDogzDAO().findByUname(user.getId());
//		for (Dogz dogz : all) {
//			IDogz<Dogz> d = (IDogz<Dogz>) Factory.get("dogz");
//			d.setDTO(dogz);
//			dogzs.put(d.getTypeId(), d);
//		}
//	}
//
//	@Override
//	public int getBlueSpirit() {
//		User user = getUser();
//		Spirit s = user.getSpirit();
//		return s.getSpiritBlue();
//	}
//
//	private User getUser() {
//		return (User) user;
//	}
//
//	@Override
//	public int getRedSpirit() {
//		User user = getUser();
//		Spirit s = user.getSpirit();
//		return s.getSpiritRed();
//	}
//
//	@Override
//	public int getGreenSpirit() {
//		User user = getUser();
//		Spirit s = user.getSpirit();
//		return s.getSpiritGreen();
//	}
//
//	@Override
//	public IDogz<Dogz> getDogz(int dId) {
//
//		return getAllLives().get(dId);
//
//	}
//
//	@Override
//	public Instructor<Dogz> getInstructor() {
//		return new InstructorImpl();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public void addDogzBody(int id) {
//
//		//第一个神兽, 自动出战并激活
//		boolean isFighting = getAllLives().size() == 0;
//		boolean isActive = getAllLives().size() == 0;
//
//		if (dogzs.containsKey(id)) {
//			throw new ClientError(S.S11066);
//		}
//
//		DogzTemplet dt = DogzTempletConfig.get(id);
//
//		if(dt == null) {
//			throw new NullPointerException("神兽不存在:" + id);
//		}
//
//
//		Dogz dogz = new Dogz();
//		dogz.setUname(user.getId());
//		dogz.setTypeId(id);
//		dogz.setStep(dt.getInitQuality());
//		dogz.setLevel(dt.getInitLevel());
//		dogz.setSkillPoint(dt.getSkillPoint());
//		dogz.setActiveSkillLevel(dt.getInitSkillLevel());
//		dogz.setIsActivate(isActive);
//		dogz.setIsFighting(isFighting);
//
//
//		DaoFactory.getUserDogzDAO().add(dogz);
//
//		IDogz<Dogz> d = (IDogz<Dogz>) Factory.get("dogz");
//		d.setDTO(dogz);
//
//		dogzs.put(d.getTypeId(), d);
//	}
//
//	@Override
//	public IDogz<Dogz> getFighting() {
//		for (IDogz<Dogz> dogz : this) {
//			if (dogz.isFighting()) {
//				return dogz;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public DogzService<Dogz> getDogzService() {
//		if (service == null) {
//			service = new DogzServiceImpl();
//
//		}
//
//		return service;
//	}
//
//	@Override
//	public AdditionMultiplier getStepAddition() {
//		IDogz<Dogz> dogz = getFighting();
//		if(dogz == null) {
//			return new AdditionMultiplierEmpty();
//		}
//
//		return dogz.getStepAddition();
//	}
//
//	/**
//	 * 神兽教练员
//	 * @author 	林岑
//	 * @time	2013-6-19
//	 */
//	private class InstructorImpl extends AbstractInstructor implements Instructor<Dogz> {
//
//		public InstructorImpl() {
//			super(getUser());
//		}
//
//		@Override
//		public void cancel(int godId) {
//			int index = findIndex(godId);
//
//			info.setEndTime(index, System.currentTimeMillis());
//			info.setTypeId(index, D.PRACTICE_GRID_EMPTY);
//			info.setModelId(index, -1);
//			info.setTimeId(index, -1);
//
//			getDAO().update(info);
//		}
//
//		@Override
//		public void addExpPracticing() {
//			float D = MainTasks.PRACTICE_RATE / (float)Time.MILES_ONE_MIN;
//
//			for (IPracticeBean p : getPracticesNow()) {
//				Body body = getBody(p.getTypeId());
//
//				if(body == null) {
//					throw new NullPointerException("body typeId:" + p.getTypeId() + ", " + user.getId());
//				}
//
//				DogzPracticeExpTemplet pt = DogzPracticeExpTempletConfig.get(body.getLevel());
//				DogzPracticeGradeTemplet pat = DogzPracticeGradeTempletConfig.get(p.getMode());
//
//				if(pt == null || pat == null) {
//					throw new NullPointerException("body level:" + body.getLevel() + ",  pMode" + p.getMode() + ", " + pat + ", " +  pt);
//				}
//
//				long exp = (long) (D * pt.getAloneExp() * (1 + pat.getAddition()));
//
//
//
//				body.addExp((int) exp);
//				body.commit();
//			}
//		}
//
//		@Override
//		protected void init() {
//		}
//
//		@Override
//		protected void startPractice(int typeId, int mId, int tId) {
//
//			int index = getFirstEmptyIndex();
//
//			info.setEndTime(index, getEndTime(tId));
//			info.setModelId(index, mId);
//			info.setTimeId(index, tId);
//			info.setTypeId(index, typeId);
//
//			getDAO().update(info);
//		}
//
//		@Override
//		protected int getHour(int tId) {
//			return DogzPracticeTimeTempletConfig.get(tId).getTime();
//		}
//
//		@Override
//		protected int getSpeedUpCdAdd() {
//			return D.DOGZ_SPEED_UP_CD_ADD;
//		}
//
//		@Override
//		protected int getSpeedUpCdAll() {
//			return D.DOGZ_SPEED_UP_CD_ALL;
//		}
//
//		@Override
//		public void clearColdDown() {
//			ColdDown cd = getCD(1);
//			int min = cd.getRemainingMin();
//			new CheckerUser(getUser()).checkCoupons(min);
//
//			cd.clear();
//			getUser().deductCoupons(min, new NormalMessage(S.S92005) + "");
//		}
//
//		@Override
//		protected IPracticeData createDTO() {
//			return new DogzPractice();
//		}
//
//		@Override
//		protected int getPracticeSize() {
//			return getDogzs().size();
//		}
//
//		@Override
//		protected void throwIsPractice() {
//			throw new ClientError(S.S11072);
//		}
//
//		@Override
//		protected IWares getTimeTemplet(int tId) {
//			return DogzPracticeTimeTempletConfig.get(tId);
//		}
//
//		@Override
//		protected IWares getGradeTemplet(int mId) {
//			return DogzPracticeGradeTempletConfig.get(mId);
//		}
//
//		@Override
//		protected void checkMaxModelAndTime(int mId, int tId) {
//		}
//
//		@Override
//		protected void removeCache(int godId) {
//		}
//
//		@Override
//		protected Body getBody(int godTypeId) {
//			return getDogz(godTypeId);
//		}
//
//		@Override
//		protected SpeedUpTemplet getSpeedUpTemplet(int level) {
//			return DogzPracticeSpeedUpTempletConfig.get(level);
//		}
//
//		@Override
//		protected IWares getOpenGridTemplet(int i) {
//			return DogzPracticeOpenGridTempletConfig.get(i);
//		}
//
//		@Override
//		protected boolean isPractice(int typeId) {
//			return getDogzs().containsKey(typeId);
//		}
//
//		@Override
//		public void practice(IDogz<Dogz> dogz, int mId, int tId) {
//			checkPractice(dogz.getTypeId(), mId, tId);
//
//			int index = getFirstEmptyIndex();
//
//			info.setModelId(index, mId);
//			info.setTimeId(index, tId);
//			info.setEndTime(index, getEndTime(tId));
//			info.setTypeId(index, dogz.getTypeId());
//
//			update();
//		}
//
//
//
//		/**
//		 * 获得所有正在修炼的神兽
//		 * @return
//		 */
//		private Map<Integer, IDogz<Dogz>> getDogzs() {
//			Map<Integer, IDogz<Dogz>> dogzs = new HashMap<>();
//			for (int i = 0; i < DogzPractice.TYPEID_LEN; i++) {
//				int typeId = info.getTypeId(i);
//				if(typeId != D.PRACTICE_GRID_EMPTY) {
//					dogzs.put(typeId, getDogz(typeId));
//				}
//			}
//			return dogzs;
//		}
//
//		@Override
//		protected IPracticeBean getPracticeData(int typeId) {
//			List<IPracticeBean> all = getPracticesNow();
//			for (IPracticeBean p : all) {
//				if(p.getTypeId() == typeId) {
//					return p;
//				}
//			}
//			return null;
//		}
//
//		@Override
//		protected DAO<String, IPracticeData> getDAO() {
//			return DaoFactory.getDogzPracticeDAO();
//		}
//	}
//
//
//
//	@Override
//	public void setOwner(User user) {
//		this.user = user;
//	}
//}
