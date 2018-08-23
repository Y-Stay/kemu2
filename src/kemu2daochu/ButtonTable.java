package kemu2daochu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;
public class ButtonTable extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable table = null;
	private DefaultTableModel model=null;
	private JScrollPane js=null ;
	public ButtonTable(){
		model = new DefaultTableModel(3,3);
		table = new JTable(model);
		table.getColumnModel().getColumn(1).setCellEditor(new MyRender());//设置编辑器
		table.getColumnModel().getColumn(1).setCellRenderer(new MyRender() );
		js = new JScrollPane(table);
this.add(js);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(399, 300);
		this.setLocationRelativeTo(null);
	}
public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ButtonTable();
	}
}
//渲染 器  编辑器
class MyRender extends AbstractCellEditor implements TableCellRenderer,ActionListener, TableCellEditor{
	private static final long serialVersionUID = 1L;
	private JButton button =null;
	public MyRender(){
		button = new JButton("确定不？");
		button.addActionListener(this);
	}
@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		return button;
	}
@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "渲染器学期", "消息", JOptionPane.OK_OPTION);
		
	}
@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return button;
	}
	
}