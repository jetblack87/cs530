package org.dataart.view.output.visual.subpanels;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;

import javax.swing.JDialog;

import org.dataart.model.Data;
import org.dataart.util.DataHelper;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

@SuppressWarnings("serial")
public class BubbleGraphDialog extends JDialog {

	private Data data;
	char[] fieldKeys = new char[]{'x','y','z'};
	HashMap<Character, String> fieldMap = new HashMap<Character, String>();
	HashMap<Character, Integer> lowerBoundsMap = new HashMap<Character, Integer>();
	HashMap<Character, Integer> upperBoundsMap = new HashMap<Character, Integer>();
	
	int normalizedUpperBound;
	int normalizedLowerBound;
	
	/**
	 * Create the dialog.
	 */
	public BubbleGraphDialog(Data data, String xInputField, String yInputField, String zInputField) {
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		this.data = data;
		fieldMap.put('x', xInputField);
		fieldMap.put('y', yInputField);
		fieldMap.put('z', zInputField);
		
		// Set to full screen
		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
		setLayout(new BorderLayout(0, 0));
		final JFXPanel fxPanel = new JFXPanel();
		add(fxPanel, BorderLayout.CENTER);
		
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
	}
	
    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
		this.setCursor(Cursor.getDefaultCursor());
    }
    

    private Scene createScene() {

    	Data normalizedData = normalizeData();
    	
    	int tick = (normalizedUpperBound - normalizedLowerBound) / 50;
    	if(tick == 0) {tick=1;}
    	
        final NumberAxis xAxis = new NumberAxis(normalizedLowerBound, normalizedUpperBound, tick);
        final NumberAxis yAxis = new NumberAxis(normalizedLowerBound, normalizedUpperBound, tick);
        final BubbleChart<Number,Number> blc = new
            BubbleChart<Number,Number>(xAxis,yAxis);
        blc.setTitle("Bubble Graph");
        
        xAxis.setLabel(fieldMap.get('x'));
        yAxis.setLabel(fieldMap.get('y'));

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(normalizedData.getDataSource() + " Data (bubble size from " + fieldMap.get('z') + " field)");

        // Add all of the elements
        for(HashMap<String, Object> element : normalizedData){
        	int x = Data.getNumberValue(element.get(fieldMap.get('x'))).intValue();
        	int y = Data.getNumberValue(element.get(fieldMap.get('y'))).intValue();
        	int z = Data.getNumberValue(element.get(fieldMap.get('z'))).intValue();
        	series1.getData().add(
					new XYChart.Data(x, y, z));
        }
                       
        Scene scene  = new Scene(blc);
        blc.getData().addAll(series1);           
        
        return (scene);
    }
    
    private Data normalizeData() {
    	Data returnData = new Data(this.data);

    	double b = 100; // new upper bound
    	double a = 0;   // new lower bound

    	for(char fieldKey : fieldKeys) {
    		upperBoundsMap.put(fieldKey, DataHelper.getUpperBound(returnData, fieldMap.get(fieldKey)));
    		lowerBoundsMap.put(fieldKey, DataHelper.getLowerBound(returnData, fieldMap.get(fieldKey)));

			int B = upperBoundsMap.get(fieldKey);
			int A = lowerBoundsMap.get(fieldKey);

			String inputFieldName = fieldMap.get(fieldKey); 

			for(HashMap<String, Object> element : returnData){
    			double oldValue = Data.getNumberValue(element.get(inputFieldName)).intValue();
				double newValue = DataHelper.normalizeNumber(a, b, A, B, oldValue);
    			if(fieldKey == 'z') {
    				// Scale smaller for 'z'
    				newValue *= .75;
    			}
    			element.put(inputFieldName, new Integer((int)newValue));
        	}
    	}
    	

    	normalizedUpperBound = (int)b;
    	normalizedLowerBound = (int)a;
    	
       	return returnData;
    }
    
}
