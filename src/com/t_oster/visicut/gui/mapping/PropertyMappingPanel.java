/**
 * This file is part of VisiCut.
 * Copyright (C) 2011 - 2013 Thomas Oster <thomas.oster@rwth-aachen.de>
 * RWTH Aachen University - 52062 Aachen, Germany
 *
 *     VisiCut is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     VisiCut is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with VisiCut.  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.t_oster.visicut.gui.mapping;

import com.t_oster.visicut.VisicutModel;
import com.t_oster.visicut.model.graphicelements.GraphicSet;
import com.t_oster.visicut.model.mapping.Mapping;
import com.t_oster.visicut.model.mapping.MappingSet;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author thommy
 */
public class PropertyMappingPanel extends javax.swing.JPanel implements PropertyChangeListener
{

  /**
   * Checks if the mapping is a property-mapping (like Color=red->A,Color=green->B) and
   * returns the Property (Color in this case). Null otherwise
   * @param ms
   * @return The property for this mapping or null if it is not a property mapping
   */
  public static String getPropertyMappingProperty(MappingSet ms)
  {
    String attribute = null;
    if (ms == null)
    {
      return null;
    }
    for (Mapping m : ms)
    {
      if (m.getFilterSet() == null)//everything-else-mapping
      {
        continue;
      }
      if (m.getFilterSet().size() != 1)
      {
        return null;
      }
      else if (attribute != null && !attribute.equals(m.getFilterSet().getFirst().getAttribute()))
      {
        return null;
      }
      attribute = m.getFilterSet().getFirst().getAttribute();
    }
    return attribute;
  }
  
  /**
   * Creates new form PropertyMappingPanel
   */
  public PropertyMappingPanel()
  {
    initComponents();
    this.cbMapBy.setRenderer(new DefaultListCellRenderer(){

      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
      {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (c instanceof JLabel && value instanceof String)
        {
          ((JLabel) c).setText(FilterSetCellEditor.translateAttVal((String) value));
        }
        return c;
      }
      
    });
    VisicutModel.getInstance().addPropertyChangeListener(this);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents()
  {

    lbMapBy = new javax.swing.JLabel();
    cbMapBy = new javax.swing.JComboBox();
    propertyMappingPanelTable = new com.t_oster.visicut.gui.mapping.PropertyMappingPanelTable();

    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/mapping/resources/PropertyMappingPanel"); // NOI18N
    lbMapBy.setText(bundle.getString("MAP_BY")); // NOI18N

    cbMapBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    cbMapBy.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        cbMapByActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(lbMapBy)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbMapBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addComponent(propertyMappingPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cbMapBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lbMapBy))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(propertyMappingPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void cbMapByActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbMapByActionPerformed
  {//GEN-HEADEREND:event_cbMapByActionPerformed
    if (!ignoreUiUpdates)
    {
      VisicutModel.getInstance().getSelectedPart().setMapping(null);
      this.propertyMappingPanelTable.setAttribute((String) this.cbMapBy.getSelectedItem());
    }
  }//GEN-LAST:event_cbMapByActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox cbMapBy;
  private javax.swing.JLabel lbMapBy;
  private com.t_oster.visicut.gui.mapping.PropertyMappingPanelTable propertyMappingPanelTable;
  // End of variables declaration//GEN-END:variables

  private boolean ignorePartUpdate = false;
  public void propertyChange(PropertyChangeEvent pce)
  {
    if (pce.getSource().equals(VisicutModel.getInstance()))
    {
      if (VisicutModel.PROP_SELECTEDPART.equals(pce.getPropertyName()))
      {
        if (VisicutModel.getInstance().getSelectedPart() != null)
        {
          this.representMapping(VisicutModel.getInstance().getSelectedPart().getMapping());
        }
      }
      else if (VisicutModel.PROP_PLF_PART_UPDATED.equals(pce.getPropertyName()) && pce.getNewValue().equals(VisicutModel.getInstance().getSelectedPart()))
      {
        if (!ignorePartUpdate)
        {
          this.representMapping(VisicutModel.getInstance().getSelectedPart().getMapping());
        }
      }
    }
  }

  private void updateComboBoxContents(String attr)
  {
    GraphicSet go = VisicutModel.getInstance().getSelectedPart().getGraphicObjects();
    //refresh contents of the comboBox to only contain properties present in the file
    Object selected = this.cbMapBy.getSelectedItem();
    this.cbMapBy.removeAllItems();
    boolean attributeAdded = false;
    for (String a : go.getAttributes())
    {
      //only makes sense if at least two properties are present
      if (go.getAttributeValues(a).size() > 1)
      {
        this.cbMapBy.addItem(a);
        if (attr == null || attr.equals(a))
        {
          attributeAdded = true;
        }
      }
    }
    if (!attributeAdded)
    {
      //we need at least the attribute even if the file does not contain it
      this.cbMapBy.addItem(attr);
    }
    this.cbMapBy.setSelectedItem(selected);
  }
  
  private boolean ignoreUiUpdates = false;
  private void representMapping(MappingSet mapping)
  {
    ignoreUiUpdates = true;
    String attr = getPropertyMappingProperty(mapping);
    updateComboBoxContents(attr);
    if (attr != null)
    {
      this.cbMapBy.setSelectedItem(attr);
    }
    ignoreUiUpdates = false;
    if (mapping == null && attr == null)
    {
      this.propertyMappingPanelTable.setAttribute((String) this.cbMapBy.getSelectedItem());
    }
  }
}
