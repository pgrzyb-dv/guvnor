/*
 * Copyright 2011 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.drools.guvnor.client.decisiontable.widget;

import org.drools.guvnor.client.widgets.decoratedgrid.CellValue;
import org.drools.guvnor.client.widgets.decoratedgrid.DecoratedGridHeaderWidget;
import org.drools.guvnor.client.widgets.decoratedgrid.DecoratedGridSidebarWidget;
import org.drools.guvnor.client.widgets.decoratedgrid.SelectedCellChangeEvent;
import org.drools.guvnor.client.widgets.decoratedgrid.SelectedCellChangeHandler;
import org.drools.guvnor.client.widgets.decoratedgrid.VerticalDecoratedGridSidebarWidget;
import org.drools.guvnor.client.widgets.decoratedgrid.VerticalDecoratedGridWidget;
import org.drools.ide.common.client.modeldriven.SuggestionCompletionEngine;
import org.drools.ide.common.client.modeldriven.dt.DTColumnConfig;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A Vertical Decision Table composed of a VerticalDecoratedGridWidget
 */
public class VerticalDecisionTableWidget extends AbstractDecisionTableWidget {

    public VerticalDecisionTableWidget(DecisionTableControlsWidget ctrls,
                                       SuggestionCompletionEngine sce) {
        super( ctrls,
               sce );

        VerticalPanel vp = new VerticalPanel();

        // Construct the widget from which we're composed
        widget = new VerticalDecoratedGridWidget<DTColumnConfig>();
        DecoratedGridHeaderWidget<DTColumnConfig> header = new VerticalDecisionTableHeaderWidget( widget );
        DecoratedGridSidebarWidget<DTColumnConfig> sidebar = new VerticalDecoratedGridSidebarWidget<DTColumnConfig>( widget,
                                                                                                                     this );
        widget.setHeaderWidget( header );
        widget.setSidebarWidget( sidebar );
        widget.setHasSystemControlledColumns( this );

        widget.getGridWidget().addSelectedCellChangeHandler( new SelectedCellChangeHandler() {

            public void onSelectedCellChange(SelectedCellChangeEvent event) {

                CellValue< ? > cell = widget.getGridWidget().getData().get( event.getCellSelectionDetail().getCoordinate() );
                dtableCtrls.getOtherwiseButton().setEnabled( canAcceptOtherwiseValues( cell ) );
            }

        } );

        vp.add( widget );
        vp.add( ctrls );
        initWidget( vp );
    }

}
