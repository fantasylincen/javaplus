package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.Protocols.CardP;

public interface IPockerBox {

	public abstract void addCard(CardActor actor);

	public abstract List<CardActor> getCards();

	public abstract void showCards(List<CardP> cards);

	public abstract void sortCards();

	public abstract void remove(List<CardP> cards);

	public abstract void adjustPockersPosition();

	public abstract List<Integer> getSelectedIds();

	public abstract List<Integer> getSelectedIds(List<CardActor> cards);

	public abstract List<CardActor> getSelectedCards();

	public abstract void remove(int id);

	public abstract void clear();

}