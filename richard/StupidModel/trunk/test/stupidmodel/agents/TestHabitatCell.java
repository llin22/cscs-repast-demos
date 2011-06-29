/*
 * Version info:
 *     $HeadURL$
 *     $LastChangedDate$
 *     $LastChangedRevision$
 *     $LastChangedBy$
 */
package stupidmodel.agents;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.RunState;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.TestGridCellFactory;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;
import stupidmodel.common.CellData;
import stupidmodel.common.Constants;

/**
 * Simple tests for the created {@link HabitatCell} agents.
 * 
 * @author Richard O. Legendi (richard.legendi)
 * @since 2.0-beta, 2011
 * @version $Id: TestHabitatCell.java 428 2011-06-18 14:19:31Z
 *          richard.legendi@gmail.com $
 * @see HabitatCell
 */
public class TestHabitatCell {

	/**
	 * Test if constructor get an invalid parameter (<code>null</code>).
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCreation() {
		new HabitatCell(null); // Should fail
	}

	/**
	 * {@link HabitatCell} objects cannot be created with negative
	 * <code>x</code> coordinate.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoordinateX() {
		final int x = RandomHelper.nextIntFromTo(Integer.MIN_VALUE, -1);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		new HabitatCell(new CellData(x, y, 1.0));
	}

	/**
	 * {@link HabitatCell} objects cannot be created with negative
	 * <code>y</code> coordinate.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoordinateY() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(Integer.MIN_VALUE, -1);

		new HabitatCell(new CellData(x, y, 1.0));
	}

	/**
	 * Test if the constructor sets the <code>x</code> coordinate for a
	 * {@link Bug} correctly.
	 */
	@Test
	public void testHabitatCellCreationCorrectX() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));
		Assert.assertEquals(x, cell.x);
	}

	/**
	 * Test if the constructor sets the <code>y</code> coordinate for a
	 * {@link Bug} correctly.
	 */
	@Test
	public void testHabitatCellCreationCorrectY() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));
		Assert.assertEquals(y, cell.y);
	}

	/**
	 * Test if the constructor sets the <code>maximumFoodProductionRate</code>
	 * for a {@link Bug} correctly.
	 */
	@Test
	public void testDefaultFoodProductionRate() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 0.01));
		Assert.assertEquals(0.01, cell.foodProductionRate, Constants.DELTA);
	}

	/**
	 * Test invalid parameter for {@link HabitatCell#foodProductionRate}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetDefaultFoodProductionRateFailure() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final double wrongValue = RandomHelper.nextDoubleFromTo(
				-Double.MAX_VALUE, -Double.MIN_VALUE);

		new HabitatCell(new CellData(x, y, wrongValue));
	}

	/**
	 * Test if {@link HabitatCell#foodProductionRate} works properly.
	 */
	@Test
	public void testSetDefaultFoodProductionRateWorks() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final double value = RandomHelper.nextDoubleFromTo(0.0,
				Double.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, value));
		Assert.assertEquals(value, cell.getFoodProductionRate(),
				Constants.DELTA);
	}

	/**
	 * Test if {@link HabitatCell#getFoodAvailability()} works properly.
	 */
	@Test
	public void testDefaultGetFoodAvailability() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 0.0));

		Assert.assertEquals(0.0, cell.getFoodAvailability(), Constants.DELTA);
	}

	/**
	 * Test invalid parameter for
	 * {@link HabitatCell#setFoodAvailability(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetFoodAvailabilityFailure() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));

		final double wrongValue = RandomHelper.nextDoubleFromTo(
				-Double.MAX_VALUE, -Double.MIN_VALUE);

		cell.setFoodAvailability(wrongValue);
	}

	/**
	 * Test if {@link HabitatCell#setFoodAvailability(double)} works properly.
	 */
	@Test
	public void testSetFoodAvailability() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));

		final double value = RandomHelper.nextDoubleFromTo(0.0,
				Double.MAX_VALUE);

		cell.setFoodAvailability(value);
		Assert.assertEquals(value, cell.getFoodAvailability(), Constants.DELTA);
	}

	/**
	 * Test if setting the food production rate of a cell to an invalid value.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFoodProductionRateFailure() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));

		final double wrongValue = RandomHelper.nextDoubleFromTo(
				-Double.MAX_VALUE, -Double.MIN_VALUE);

		cell.setFoodProductionRate(wrongValue);
	}

	/**
	 * Test if setting the food production rate works as expected.
	 */
	@Test
	public void testSetFoodProductionRate() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));

		final double value = RandomHelper.nextDoubleFromTo(0.0,
				Double.MAX_VALUE);

		cell.setFoodProductionRate(value);
		Assert.assertEquals(value, cell.getFoodProductionRate(),
				Constants.DELTA);
	}

	/**
	 * Test if cell is in a dummy context without being assigned a
	 * <code>GridValueLayer</code>.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDefaultGrowFoodWithoutProperGridValueLayer() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));

		{ // Some workaround to make the test see like if it was running
			final Context<Object> context = new DefaultContext<Object>();

			context.add(cell);
			RunState.init().setMasterContext(context);
		}

		cell.growFood();
	}

	/**
	 * Test if food grows as expected on a cell.
	 */
	@Test
	public void testDefaultGrowFood() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final double value = RandomHelper.nextDoubleFromTo(0, Double.MAX_VALUE);
		final HabitatCell cell = new HabitatCell(new CellData(x, y, value));

		final GridValueLayer valueLayer = mock(GridValueLayer.class);
		when(valueLayer.getName()).thenReturn(Constants.FOOD_VALUE_LAYER_ID);

		{ // Some workaround to make the test see like if it was running
			final Context<Object> context = new DefaultContext<Object>();

			context.add(cell);
			context.addValueLayer(valueLayer);
			RunState.init().setMasterContext(context);
		}

		final double prevFood = cell.getFoodAvailability();

		cell.growFood();

		Assert.assertEquals(prevFood + cell.getFoodProductionRate(),
				cell.getFoodAvailability(), Constants.DELTA);
	}

	/**
	 * Test invalid parameter (<i>negative value</i>) for
	 * {@link HabitatCell#foodConsumed(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFoodConsumedFailure() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));
		final double wrongValue = RandomHelper.nextDoubleFromTo(
				-Double.MAX_VALUE, -Double.MIN_VALUE);
		cell.foodConsumed(wrongValue);
	}

	/**
	 * Test invalid parameter (<i>greater than available consumption</i>) for
	 * {@link HabitatCell#foodConsumed(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMoreThanAvailableFoodConsumed() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));

		final double wrongValue = RandomHelper.nextDoubleFromTo(
				cell.getFoodAvailability(), Double.MAX_VALUE);
		cell.foodConsumed(wrongValue);
	}

	/**
	 * Test if food consumption modifies the food availability correctly.
	 */
	@Test
	public void testFoodConsumed() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));

		final double food = RandomHelper
				.nextDoubleFromTo(0.0, Double.MAX_VALUE);

		final double consumed = RandomHelper.nextDoubleFromTo(0.0, food);

		cell.setFoodAvailability(food);
		cell.foodConsumed(consumed);

		Assert.assertEquals(food - consumed, cell.getFoodAvailability(),
				Constants.DELTA);
	}

	/**
	 * Just to suppress <code>toString()</code> coverage noise.
	 */
	@Test
	public void testToString() {
		final int x = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);
		final int y = RandomHelper.nextIntFromTo(0, Integer.MAX_VALUE);

		final HabitatCell cell = new HabitatCell(new CellData(x, y, 1.0));
		Assert.assertNotNull(cell.toString());
	}

	/**
	 * A simple test to verify if the defined comparator for {@link HabitatCell}
	 * objects order elements descending properly.
	 */
	@Test
	public void testComparatorBasicFunctionality() {
		final ArrayList<GridCell<HabitatCell>> list = new ArrayList<GridCell<HabitatCell>>();

		final HabitatCell cell = new HabitatCell(new CellData(0, 0, 1.0));
		cell.setFoodAvailability(10);
		final GridCell<HabitatCell> gc1 = TestGridCellFactory
				.createGridCellToTest(new GridPoint(0, 0), HabitatCell.class,
						cell);
		list.add(gc1);

		final HabitatCell bell = new HabitatCell(new CellData(1, 1, 1.0));
		bell.setFoodAvailability(20);
		final GridCell<HabitatCell> gc2 = TestGridCellFactory
				.createGridCellToTest(new GridPoint(1, 1), HabitatCell.class,
						bell);
		list.add(gc2);

		Collections.sort(list,
				HabitatCell.HABITAT_CELL_FOOD_AVAILABILITY_COMPARATOR);
		Assert.assertEquals(gc2, list.get(0));
		Assert.assertEquals(gc1, list.get(1));
	}

	/**
	 * Another simple test to verify if the defined comparator for
	 * {@link HabitatCell} objects order elements descending properly.
	 * 
	 * @see #testComparatorBasicFunctionality()
	 */
	@Test
	public void testComparator() {
		final ArrayList<GridCell<HabitatCell>> list = new ArrayList<GridCell<HabitatCell>>();

		final HabitatCell cell = new HabitatCell(new CellData(0, 0, 1.0));
		cell.setFoodAvailability(15);
		final GridCell<HabitatCell> gc1 = TestGridCellFactory
				.createGridCellToTest(new GridPoint(0, 0), HabitatCell.class,
						cell);
		list.add(gc1);

		final HabitatCell bell = new HabitatCell(new CellData(1, 1, 1.0));
		bell.setFoodAvailability(10);
		final GridCell<HabitatCell> gc2 = TestGridCellFactory
				.createGridCellToTest(new GridPoint(1, 1), HabitatCell.class,
						bell);
		list.add(gc2);

		final HabitatCell dell = new HabitatCell(new CellData(2, 2, 1.0));
		dell.setFoodAvailability(20);
		final GridCell<HabitatCell> gc3 = TestGridCellFactory
				.createGridCellToTest(new GridPoint(2, 2), HabitatCell.class,
						dell);
		list.add(gc3);

		Collections.sort(list,
				HabitatCell.HABITAT_CELL_FOOD_AVAILABILITY_COMPARATOR);
		Assert.assertEquals(gc3, list.get(0));
		Assert.assertEquals(gc1, list.get(1));
		Assert.assertEquals(gc2, list.get(2));
	}

}
