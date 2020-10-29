
function func_${param.chartName}() {
	var ${param.chartName} = new CanvasJS.Chart("${param.chartName}_Container", {
		theme: "light2",
		animationEnabled: true,
		 backgroundColor: "#EBF9F5",
		title:{
			text: "${param.titleText}"
		},
		data: [{
			type: "pie",
			showInLegend: true,
			legendText: "{lbl}",
			toolTipContent: "{lbl}: <strong>{y}</strong>",
			indexLabel: "{lbl}: #percent%",
			dataPoints : ${param.chartJSON}
		}]
	});
	${param.chartName}.render();
}	