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
import com.t_oster.visicut.managers.MappingManager;
import com.t_oster.visicut.misc.DialogHelper;
import com.t_oster.visicut.model.mapping.MappingSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This panel just hosts the predefinedMappingBox, customMappingPanel and propertyMappingPanel
 * It hides the latter two until their option is selected in the predefinedMappingBox
 * 
 * @author Thomas Oster <thomas.oster@rwth-aachen.de>
 */
public class MappingPanel extends javax.swing.JPanel
{

  private DialogHelper dialog;
  
  /**
   * Creates new form MappingPanel
   */
  public MappingPanel()
  {
    initComponents();
    dialog = new DialogHelper(this, "VisiCut");
    this.customMappingPanel.setLoadButtonVisible(false);
    this.propertyMappingPanel.setLoadButtonVisible(false);
    this.customMappingPanel.getSaveButton().addActionListener(saveMappingActionListener);
    this.propertyMappingPanel.getSaveButton().addActionListener(saveMappingActionListener);
  }

  private ActionListener saveMappingActionListener = new ActionListener()
  {
    public void actionPerformed(ActionEvent ae)
    {
      if (VisicutModel.getInstance().getSelectedPart() == null)
      {
        return;
      }
      MappingSet mapping = VisicutModel.getInstance().getSelectedPart().getMapping();
      if (mapping == null)
      {
        return;
      }
      String name = dialog.askString(mapping.getName(), java.util.ResourceBundle.getBundle("com/t_oster/visicut/gui/mapping/resources/MappingPanel").getString("NAME_FOR_MAPPING"));
        if (name != null)
        {
          MappingSet ms = mapping.clone();
          ms.setName(name);
          try
          {
            MappingManager.getInstance().add(ms);
          }
          catch (Exception ex)
          {
            MappingPanel.this.dialog.showErrorMessage(ex);
          }
        }
    }
  };
  
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents()
  {

    predefinedMappingBox = new com.t_oster.visicut.gui.mapping.PredefinedMappingBox();
    propertyMappingPanel = new com.t_oster.visicut.gui.mapping.PropertyMappingPanel();
    customMappingPanel = new com.t_oster.visicut.gui.mapping.CustomMappingPanel();

    predefinedMappingBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        predefinedMappingBoxActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(propertyMappingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
          .addComponent(customMappingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(predefinedMappingBox, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(predefinedMappingBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(propertyMappingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(customMappingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void predefinedMappingBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_predefinedMappingBoxActionPerformed
  {//GEN-HEADEREND:event_predefinedMappingBoxActionPerformed
    Object selected = predefinedMappingBox.getSelectedItem();
    if (selected instanceof PredefinedMappingBox.MapByPropertyEntry)
    {
      String property = ((PredefinedMappingBox.MapByPropertyEntry) selected).property;
      this.propertyMappingPanel.setSelectedProperty(property);
      this.predefinedMappingBox.setSelectedItem(predefinedMappingBox.BY_PROPERTY);
      this.customMappingPanel.setVisible(false);
      this.propertyMappingPanel.setVisible(true);
    }
    else
    {
      this.customMappingPanel.setVisible(predefinedMappingBox.CUSTOM.equals(selected));
      this.propertyMappingPanel.setVisible(predefinedMappingBox.BY_PROPERTY.equals(selected));
    }
  }//GEN-LAST:event_predefinedMappingBoxActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private com.t_oster.visicut.gui.mapping.CustomMappingPanel customMappingPanel;
  private com.t_oster.visicut.gui.mapping.PredefinedMappingBox predefinedMappingBox;
  private com.t_oster.visicut.gui.mapping.PropertyMappingPanel propertyMappingPanel;
  // End of variables declaration//GEN-END:variables
}
